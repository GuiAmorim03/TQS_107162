package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import com.example.TqsStack;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TqsStackTest {

  private TqsStack stack;

  @BeforeEach
  public void setUp() {
    stack = new TqsStack();
  }

  @Test
  // a) A stack is empty on construction.
  public void testStackEmptyOnConstruction() {
    assertTrue(stack.isEmpty());
  }

  @Test
  // b) A stack has size 0 on construction.
  public void testStackHasSizeZeroOnConstruction() {
    assertEquals(0, stack.size());
  }

  @Test
  // c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
  public void testStackIsNotEmptyAndSizeIsNAfterNPushes() {
    stack.push("Aveiro");
    stack.push("Porto");
    stack.push("Lisbon");
    assertEquals(3, stack.size());
    assertTrue(!stack.isEmpty());
  }

  @Test
  // d) If one pushes x then pops, the value popped is x.
  public void testValuePoppedIsXAfterPushingX() {
    stack.push("Barcelona");
    stack.push("Madrid");
    assertEquals("Madrid", stack.pop());
  }

  @Test
  // e) If one pushes x then peeks, the value returned is x, but the size stays the same
  public void testValuePeekedIsXAfterPushingXAndSizeStaysTheSame() {
    stack.push("Paris");
    assertEquals("Paris", stack.peek());
    assertEquals(1, stack.size());
  }

  @Test
  // f) If the size is n, then after n pops, the stack is empty and has a size 0
  public void testStackIsEmptyAndSizeIsZeroAfterNPops() {
    stack.push("London");
    stack.push("Manchester");
    stack.push("Liverpool");
    stack.pop();
    stack.pop();
    stack.pop();
    assertTrue(stack.isEmpty());
    assertEquals(0, stack.size());
  }

  @Test
  // g) Popping from an empty stack does throw a NoSuchElementException [You should test for the Exception occurrence]
  public void testPoppingFromEmptyStackThrowsNoSuchElementException() {
    assertTrue(stack.isEmpty());
    assertThrows(NoSuchElementException.class, () -> stack.pop());
  }

  @Test
  // h) Peeking into an empty stack does throw a NoSuchElementException
  public void testPeekingIntoEmptyStackThrowsNoSuchElementException() {
    assertTrue(stack.isEmpty());
    assertThrows(NoSuchElementException.class, () -> stack.peek());
  }

  @Test
  // i) For bounded stacks only: pushing onto a full stack does throw an IllegalStateException
  public void testPushingOntoFullStackThrowsIllegalStateException() {
    stack.push("New York");
    stack.push("New Jersey");
    stack.push("Chicago");
    stack.push("Los Angeles");
    stack.push("Las Vegas");
    assertThrows(
      IllegalStateException.class,
      () -> stack.push("San Francisco")
    );
  }
}
/*
What to test1:
a) A stack is empty on construction.
b) A stack has size 0 on construction.
c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
d) If one pushes x then pops, the value popped is x.
e) If one pushes x then peeks, the value returned is x, but the size stays the same
f) If the size is n, then after n pops, the stack is empty and has a size 0
g) Popping from an empty stack does throw a NoSuchElementException [You should test for the Exception occurrence]
h) Peeking into an empty stack does throw a NoSuchElementException
i) For bounded stacks only: pushing onto a full stack does throw an IllegalStateException
*/
