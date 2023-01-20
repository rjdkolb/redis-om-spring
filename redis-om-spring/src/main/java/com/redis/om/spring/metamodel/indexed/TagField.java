package com.redis.om.spring.metamodel.indexed;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.ToLongFunction;

import com.redis.om.spring.metamodel.MetamodelField;
import com.redis.om.spring.search.stream.actions.ArrayAppendAction;
import com.redis.om.spring.search.stream.actions.ArrayIndexOfAction;
import com.redis.om.spring.search.stream.actions.ArrayInsertAction;
import com.redis.om.spring.search.stream.actions.ArrayLengthAction;
import com.redis.om.spring.search.stream.actions.ArrayPopAction;
import com.redis.om.spring.search.stream.actions.ArrayTrimAction;
import com.redis.om.spring.search.stream.predicates.tag.ContainsAllPredicate;
import com.redis.om.spring.search.stream.predicates.tag.EqualPredicate;
import com.redis.om.spring.search.stream.predicates.tag.InPredicate;
import com.redis.om.spring.search.stream.predicates.tag.NotEqualPredicate;

public class TagField<E, T> extends MetamodelField<E, T> {

  public TagField(Field field, boolean indexed) {
    super(field, indexed);
  }
  
  public EqualPredicate<? super E,T> eq(T value) {
    return new EqualPredicate<>(field,value);
  }
  
  public NotEqualPredicate<? super E,T> notEq(T value) {
    return new NotEqualPredicate<>(field,value);
  }
  
  public NotEqualPredicate<? super E,T> notEq(String... values) {
    return new NotEqualPredicate<>(field, Arrays.asList(values));
  }
  
  public InPredicate<? super E, ?> in(String... values) {
    return new InPredicate<>(field, Arrays.asList(values));
  }
  
  public ContainsAllPredicate<? super E, ?> containsAll(String... values) {
    return new ContainsAllPredicate<>(field, Arrays.asList(values));
  }
  
  public NotEqualPredicate<? super E,T> containsNone(T value) {
    return new NotEqualPredicate<>(field,value);
  }
  
  public Consumer<? super E> add(Object value) {
    return new ArrayAppendAction<>(field, value);
  }

  public Consumer<? super E> insert(Object value, Integer index) {
    return new ArrayInsertAction<>(field, value, index);
  }
  
  public Consumer<? super E> prepend(Object value) {
    return new ArrayInsertAction<>(field, value, 0);
  }
  
  public ToLongFunction<? super E> length() {
    return new ArrayLengthAction<>(field);
  }

  public ToLongFunction<? super E> indexOf(Object element) {
    return new ArrayIndexOfAction<>(field, element);
  }
  
  public <R> ArrayPopAction<? super E,R> pop(Integer index) {
    return new ArrayPopAction<>(field, index);
  }
  
  public <R> ArrayPopAction<? super E,R> pop() {
    return pop(-1);
  }
  
  public <R> ArrayPopAction<? super E,R> removeFirst() {
    return pop(0);
  }
  
  public <R> ArrayPopAction<? super E,R> removeLast() {
    return pop(-1);
  }
  
  public <R> ArrayPopAction<? super E,R> remove(Integer index) {
    return pop(index);
  }

  public Consumer<? super E> trimToRange(Integer begin, Integer end) {
    return new ArrayTrimAction<>(field, begin, end);
  }

}
