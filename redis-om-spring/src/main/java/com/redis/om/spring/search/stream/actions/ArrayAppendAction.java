package com.redis.om.spring.search.stream.actions;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import redis.clients.jedis.json.Path;

public class ArrayAppendAction<E> extends BaseAbstractAction implements Consumer<E> {

  private Object value;

  public ArrayAppendAction(Field field, Object value) {
    super(field);
    this.value = value;
  }

  @Override
  public void accept(E entity) {
    json.arrAppend(getKey(entity), Path.of("." + field.getName()), value);
  }

}
