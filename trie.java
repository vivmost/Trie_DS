import java.util.*;

public class trie {
    static class trieNode {
        ArrayList<trieNode> child;
        boolean isEnding;

        public trieNode() {
            child = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                child.add(null);
            }
            isEnding = false;
        }
    }

     static class Trie {
        public trieNode root;
        Trie() {
            root = new trieNode();
        }

        public void insert(String s) {
            String ss = s.toLowerCase();
            trieNode currNode = root;
            for(int i = 0; i < ss.length(); i++) {
                int index = ss.charAt(i) - 'a';
                if(currNode.child.get(index) == null) {
                    currNode.child.set(index, new trieNode());
                }
                currNode = currNode.child.get(index);
            }
            currNode.isEnding = true;
        }

        public boolean startsWith(String s) {
            String ss = s.toLowerCase();
            trieNode currNode = root;
            for(int i = 0; i < ss.length(); i++) {
                int index = ss.charAt(i) - 'a';
                if(currNode.child.get(index) == null) {
                    return false;
                }
                currNode = currNode.child.get(index);
            }
            return true;
        }

        public boolean search(String s) {
            String ss = s.toLowerCase();
            trieNode currNode = root;
            for(int i = 0; i < ss.length(); i++) {
                int index = ss.charAt(i) - 'a';
                if(currNode.child.get(index) == null) {
                    return false;
                }
                currNode = currNode.child.get(index);
            }
            return currNode.isEnding;
        }

        public boolean isEmpty(trieNode root) {
            for(int i = 0; i < 26; i++) {
                if(root.child.get(i) != null) {
                    return false;
                }
            }
            return true;
        }

        public  trieNode deleteHelper(String s, trieNode root, int depth) {
             String ss = s.toLowerCase();
             if(root == null) return null;
             if(depth == ss.length()) {
                 if(root.isEnding) root.isEnding = false;
                 if(isEmpty(root)) root = null;
                 return root;
             }

             int index = s.charAt(depth) - 'a';
             root.child.set(index, deleteHelper(s, root.child.get(index), depth + 1));

             return root;
        }

        public trieNode delete(String s) {
            return deleteHelper(s, root, 0);
        }
    }

        public static void main(String[] args) {
            Trie mytrie = new Trie();
            mytrie.insert("search");
            if(mytrie.search("search")) System.out.println("search worked");
            if(mytrie.startsWith("sea")) System.out.println("starts_with worked");
            if(!mytrie.isEmpty(mytrie.root)) System.out.println("!isEmpty worked");
            mytrie.delete("search");
            if(!mytrie.search("search")) System.out.println("delete worked");
        }
}
