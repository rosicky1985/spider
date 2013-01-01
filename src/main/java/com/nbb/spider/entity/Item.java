package com.nbb.spider.entity;

public abstract interface Item
{
  public abstract String getTitle();

  public abstract Integer getRank();

  public abstract int compareRankto(Item paramItem);
}

