/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Heap;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {
    private MinHeap<String> mh;
    // For tests
    public class Person{
        public String name;
        public int age;
        public Person(String name, int age){
            this.name=name;
            this.age=age;
        }
    }
    public MinHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.mh=new MinHeap<String>(10);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void randomTest(){
        // Compares the minium heap output to a sorted array
        MinHeap<String> heap = new MinHeap<String>(5);
        Person[] persons = new Person[5];
        int[] ages = new int[5];
        ages[0]=1+(int)Math.round(Math.random()*10+1);
        ages[1]=ages[0]+(int)Math.round(Math.random()*10+1);
        ages[2]=ages[1]+(int)Math.round(Math.random()*10+1);
        ages[3]=ages[2]+(int)Math.round(Math.random()*10+1);
        ages[4]=ages[3]+(int)Math.round(Math.random()*10+1);
        int age = ages[0];
        persons[0]=new Person("Kalle",age);
        heap.insert(age, "Kalle");
        age = ages[1];
        persons[1]=new Person("Simo",age);
        heap.insert(age, "Simo");
        age = ages[2];
        persons[2]=new Person("Hanna",age);
        heap.insert(age, "Hanna");
        age = ages[3];
        persons[3]=new Person("Petteri",age);
        heap.insert(age, "Petteri");
        age = ages[4];
        persons[4]=new Person("Marja",age);
        heap.insert(age, "Marja");
        Arrays.sort(persons, new Comparator<Person>(){
            @Override
            public int compare(Person a, Person b){
                return a.age-b.age;
            }
        });
        for(int i=0; i<persons.length; i++){
            String top = heap.pop();
            assertEquals(persons[i].name,top);
        }
    }
    @Test
    public void standartTest() {
        this.mh.insert(15, "Kalle");
        this.mh.insert(5, "Pekka");
        this.mh.insert(14, "Simo");
        this.mh.insert(3, "Marja");
        assertEquals("Marja",this.mh.pop());
        this.mh.decrease("Kalle", 1);
        assertEquals("Kalle",this.mh.pop());
        this.mh.decrease("Pekka", 1);
        this.mh.insert(9, "Jutta");
        assertEquals("Pekka",this.mh.pop());
        assertEquals("Jutta",this.mh.pop());
        assertEquals(false,this.mh.isEmpty());
        assertEquals("Simo",this.mh.pop());
        assertEquals(true,this.mh.isEmpty());
    }
}