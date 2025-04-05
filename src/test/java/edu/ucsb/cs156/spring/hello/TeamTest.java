package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class TeamTest {

    Team team;

    @BeforeEach
    public void setup() {
        team = new Team("test-team");    
    }

    @Test
    public void getName_returns_correct_name() {
       assert(team.getName().equals("test-team"));
    }

   
    // TODO: Add additional tests as needed to get to 100% jacoco line coverage, and
    // 100% mutation coverage (all mutants timed out or killed)
    @Test
    public void toString_returns_correct_string() {
        assertEquals("Team(name=test-team, members=[])", team.toString());
    }

    @Test
    public void equalsTest() {
        assertEquals(true, team.equals(team));
    }

    @Test
    public void equalsType() {
        assertEquals(false, team.equals(5));
    }

    @Test
    public void nameAndMembersEqual() {
        Team t2 = new Team("t2");
        ArrayList<String> members = new ArrayList<>();
        members.add("BObby Chickens");
        members.add("billy bob");
        t2.setMembers(members);
        team.addMember("bob");

        Team t3 = new Team(team.getName()); 
        t3.setMembers(team.getMembers());

        Team t4 = new Team("random name"); 
        t4.setMembers(team.getMembers());

        Team t5 = new Team(team.getName()); 
        t5.setMembers(members);

        assertEquals(false, team.equals(t2));
        assertEquals(true, team.equals(t3));
        assertEquals(false, team.equals(t4));
        assertEquals(false, team.equals(t5));
    }
    @Test
    public void testHash() {
         Team t1 = new Team();
        t1.setName("foo");
        t1.addMember("bar");

        Team t2 = new Team();
        t2.setName("foo");
        t2.addMember("bar");

        assertEquals(t1, t2); // This makes the hashCode() check meaningful
        assertEquals(t1.hashCode(), t2.hashCode());
    }
    @Test
    public void testHashCode_differentTeams() {
        Team t1 = new Team();
        t1.setName("foo");
        t1.addMember("bar");

        Team t2 = new Team();
        t2.setName("bar");
        t2.addMember("baz");

        assertNotEquals(t1.hashCode(), t2.hashCode());
    }
  
    @Test
    public void testHashCode_detectsORvsANDMutation() {
        Team t1 = new Team();
        t1.setName("ABC");
        t1.addMember("XYZ");

        int nameHash = t1.getName().hashCode();
        int membersHash = t1.getMembers().hashCode();

        int expectedHash = nameHash | membersHash;
        int wrongHash = nameHash & membersHash;

        System.out.println("name.hashCode(): " + nameHash);
        System.out.println("members.hashCode(): " + membersHash);
        System.out.println("Expected (|): " + expectedHash);
        System.out.println("Wrong (&): " + wrongHash);
        System.out.println("Actual hashCode(): " + t1.hashCode());

        assertEquals(expectedHash, t1.hashCode());
        assertNotEquals(wrongHash, t1.hashCode());
    }
}
