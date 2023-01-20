package com.redis.om.spring.search.stream.predicates.tag;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import com.redis.om.spring.repository.query.QueryUtils;
import com.redis.om.spring.search.stream.predicates.BaseAbstractPredicate;

import redis.clients.jedis.search.querybuilder.Node;
import redis.clients.jedis.search.querybuilder.QueryBuilders;
import redis.clients.jedis.search.querybuilder.QueryNode;

public class ContainsAllPredicate<E, T> extends BaseAbstractPredicate<E, T> {

  private List<String> values;

  public ContainsAllPredicate(Field field, List<String> list) {
    super(field);
    this.values = list.stream().map(QueryUtils::escape).collect(Collectors.toList());
  }

  public List<String> getValues() {
    return values;
  }

  @Override
  public Node apply(Node root) {
    QueryNode and = QueryBuilders.intersect();
    for (String value : getValues()) {
      and.add(getField().getName(), "{" + value + "}");
    }

    return QueryBuilders.intersect(root, and);
  }
}
