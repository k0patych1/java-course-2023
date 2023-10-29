package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    public void incorrectClusterizeTest() {
        String s = "(()).()";

        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(s));
    }

    @Test
    public void firstClusterizeTest() {
        String s = "()()()";
        List<String> receivedClusters = Task2.clusterize(s);
        List<String> expectedClusters = new ArrayList<>();
        expectedClusters.add("()");
        expectedClusters.add("()");
        expectedClusters.add("()");
        assertThat(receivedClusters).isEqualTo(expectedClusters);
    }

    @Test
    public void secondClusterizeTest() {
        String s = "((()))";
        List<String> receivedClusters = Task2.clusterize(s);
        List<String> expectedClusters = new ArrayList<>();
        expectedClusters.add("((()))");
        assertThat(receivedClusters).isEqualTo(expectedClusters);
    }

    @Test
    public void thirdClusterizeTest() {
        String s = "((()))(())()()(()())";
        List<String> receivedClusters = Task2.clusterize(s);
        List<String> expectedClusters = new ArrayList<>();
        expectedClusters.add("((()))");
        expectedClusters.add("(())");
        expectedClusters.add("()");
        expectedClusters.add("()");
        expectedClusters.add("(()())");
        assertThat(receivedClusters).isEqualTo(expectedClusters);
    }

    @Test
    public void fourthClusterizeTest() {
        String s = "((())())(()(()()))";
        List<String> receivedClusters = Task2.clusterize(s);
        List<String> expectedClusters = new ArrayList<>();
        expectedClusters.add("((())())");
        expectedClusters.add("(()(()()))");
        assertThat(receivedClusters).isEqualTo(expectedClusters);
    }
}
