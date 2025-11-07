import java.util.Scanner;
public class Solution {
/*
   * for insertion program will split the first line then create an AVLNode for each token,
   * then it will insert the node into the tree. If a line is repeated(Check from Tree) frequency++
   * The varriables fileName and currentLine will be saved. per token a Node will be created which has
   * the previous varriables as it's attributes. A SLL will be created if it wasn't already and this node
   * will be inserted.
   */
  /* 
  1 3
  File1.txt 3
  data structures are cool
  algorithms are fun
  this is a cool project
  File2.txt 2
  search the data
  time is bubble
  File3.txt 1
  data in data is fun
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Index tree= new Index();
    while (true){
      String[] inputArr=sc.nextLine().split(" ");
      switch (inputArr[0]) {
        case "1":
          insert(inputArr,sc,tree);
          break;
        case "2":
          search(inputArr[1],tree);
          break;
        case "3":
            remove(inputArr[1],tree);
          break;
        case "4":
          tree.traverse();
          break;
        default:
          System.out.println(-1);
          }
        }

  }
  
  public static void insert(String[] arr, Scanner sc, Index tree){
    for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
      String[] fileInfo= sc.nextLine().split(" ");
      String fileName=fileInfo[0];
        for (int j = 0; j < Integer.parseInt(fileInfo[1]); j++) {
          String[] line=sc.nextLine().split(" ");
            for (int k = 0; k < line.length; k++) {
              AVLNode node=new AVLNode(line[k]);
              node.getSLL().insert(new Node(fileName, j+1));
              if (!(tree.insert(node))) {
                System.out.println(-1);
                return;
              }
            }
        }

    }
  }

  public static void search(String token,Index tree){
    AVLNode node=tree.Search(token);
    if (node==null) {
      System.out.println(-1);
    }
    else
      System.out.println(node.getFrequency());
      Node iterator=node.getSLL().getHead();
      while (iterator!=null){
        System.out.println(iterator.getFileName()+" "+iterator.getLineNumber());
        iterator=iterator.next;
      }
  }

  public static void remove(String token, Index tree){
    if (tree.remove(token)) {
      return;
    }
    else
      System.out.println(-1);
  }
/*
Output for current code:
4
File1.txt 1
a algorithms bubble cool are is search project the time this structures data
(MISSING fun)
 */
}
