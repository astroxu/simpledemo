package hbidemo.core.test3.impl;

import hbidemo.core.test3.FileChain;
import hbidemo.core.test3.FileInfo;
import hbidemo.core.test3.FileProcessor;
import hbidemo.core.test3.Uploader;

import java.util.ArrayList;
import java.util.List;

public class StandardFileChain implements FileChain {
    private List<FileInfo> fileInfos = null;
    private List<FileProcessor> processors = new ArrayList();
    private int processorIndex = 0;
    private Uploader uploader;

    public StandardFileChain(List<FileInfo> fileInfos, Uploader uploader) {
        this.fileInfos = fileInfos;
        this.uploader = uploader;
    }

    public void doProcess() throws Exception {
        if (this.processors != null && !this.processors.isEmpty() && this.processorIndex < this.processors.size()) {
            ((FileProcessor)this.processors.get(this.processorIndex++)).process(this, this.uploader, this.fileInfos);
        }

    }

    public void addProcessor(FileProcessor processor) {
        this.processors.add(processor);
    }
}

