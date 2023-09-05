package com.redis.om.spring.search.stream;

import redis.clients.jedis.search.FTSearchParams;

public class SummarizeParams {
  public Integer getFragsNum() {
    return fragsNum;
  }

  public Integer getFragSize() {
    return fragSize;
  }

  public String getSeparator() {
    return separator;
  }

  private Integer fragsNum = 3;
  private Integer fragSize = 20;
  private String separator = "...";

  public SummarizeParams fragments(int num) {
    this.fragsNum = num;
    return this;
  }

  public SummarizeParams size(int size) {
    this.fragSize = size;
    return this;
  }

  public SummarizeParams separator(String separator) {
    this.separator = separator;
    return this;
  }

  public static SummarizeParams instance() {
    return new SummarizeParams();
  }
}
