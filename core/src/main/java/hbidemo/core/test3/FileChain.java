package hbidemo.core.test3;


public interface FileChain {
    void doProcess() throws Exception;
    
    void addProcessor(FileProcessor var1);
}
