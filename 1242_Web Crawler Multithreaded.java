// single thread
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        
        // find hostname
        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;
        
        // bfs
        Set<String> result = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startUrl);
        while(!queue.isEmpty()){
            String tmp = queue.remove();
            if(tmp.startsWith(hostname) && !result.contains(tmp)){
                result.add(tmp);
                for(String s: htmlParser.getUrls(tmp)){
                    queue.add(s);
                }
            }
        }
        return new ArrayList<>(result);
    }
}

// multi-thread (maybe wrong?)
class Solution {
   public List<String> crawl(String startUrl, HtmlParser htmlParser) {
       
        // find hostname
        int index = startUrl.indexOf('/', 7);
        String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

        // multi-thread
        Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
        crawler.result = new HashSet<>(); // reset result as static property belongs to class, it will go through all of the test cases
        Thread thread = new Thread(crawler);
        thread.start();
        
        crawler.joinThread(thread); // wait for thread to complete
        return new ArrayList<>(crawler.result);
    }
}

class Crawler implements Runnable {
    String startUrl;
    String hostname;
    HtmlParser htmlParser;
    public static volatile Set<String> result = new HashSet<>();

    public Crawler(String startUrl, String hostname, HtmlParser htmlParser){
        this.startUrl = startUrl;
        this.hostname = hostname;
        this.htmlParser = htmlParser;
    }
    @Override 
    public void run(){
        if(this.startUrl.startsWith(hostname) && !this.result.contains(this.startUrl)){
            addUrl(this.result, this.startUrl);
            List<Thread> threads = new ArrayList<>();
            for(String s: htmlParser.getUrls(startUrl)){
                Crawler crawler = new Crawler(s, hostname, htmlParser);
                Thread thread = new Thread(crawler);
                thread.start();
                threads.add(thread);
            }
            for(Thread t: threads){
                joinThread(t); // wait for all threads to complete
            }
        }
    }
    public static synchronized void addUrl(Set<String> result, String url){
        result.add(url);
    }
    
    public static void joinThread(Thread thread){
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

//ConcurrentHashMap + lambda
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);
        
        Set<String> visited = ConcurrentHashMap.newKeySet();	//ConcurrentHashSet
        visited.add(startUrl);
        
        return crawl(startUrl, htmlParser, hostname, visited)
            .collect(Collectors.toList());
    }
    
    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname, Set<String> visited) {
        Stream<String> stream = htmlParser.getUrls(startUrl)
            .parallelStream()
            .filter(url -> isSameHostname(url, hostname))
            .filter(url -> visited.add(url))
            .flatMap(url -> crawl(url, htmlParser, hostname, visited));
        
        return Stream.concat(Stream.of(startUrl), stream);
    }
    
    private String getHostname(String url) {
        int idx = url.indexOf('/', 7);
        return (idx != -1) ? url.substring(0, idx) : url;
    }
    
    private boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }
}