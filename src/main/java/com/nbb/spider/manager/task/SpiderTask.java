package com.nbb.spider.manager.task;

import java.io.IOException;

public abstract interface SpiderTask
{
  public abstract void run()
    throws IOException;
}

