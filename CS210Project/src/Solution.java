import java.util.Scanner;
public class Solution {
/*
   * for insertion program will split the first line then create an AVLNode for each token,
   * then it will insert the node into the tree. If a line is repeated(Check from Tree) frequency++
   * The varriables fileName and currentLine will be saved. per token a Node will be created which has
   * the previous varriables as it's attributes. A SLL will be created if it wasn't already and this node
   * will be inserted.
   */
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Index tree= new Index();
    while (sc.hasNext()){
      try {
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
      } catch (Exception e) {
        System.out.println(-1);
      }
      
        }

  }
  
  public static void insert(String[] arr, Scanner sc, Index tree){
    for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
      //Go over every file
      String[] fileInfo= sc.nextLine().split(" ");
      String fileName=fileInfo[0];

        for (int j = 0; j < Integer.parseInt(fileInfo[1]); j++) {
          //Go over every line in the file
          String[] line=sc.nextLine().split(" ");
            for (int k = 0; k < line.length; k++) {
              //Go over every word in the file
              String token = line[k];
              AVLNode existingNode = tree.Search(token);
              
              if (existingNode == null) {
                // Token doesn't exist, create new node
                AVLNode newNode = new AVLNode(token);
                if (!(tree.insert(newNode))) {
                  System.out.println(-1);
                  return;
                }
                existingNode = newNode;
              } else {
                // Token exists, increment frequency
                existingNode.incFreq();
              }
              // Add file/line info to the node's SLL
              existingNode.getSLL().insert(new Node(fileName, j+1));
            }
        }

    }
  }

  public static void search(String token,Index tree){
    AVLNode node=tree.Search(token);
    if (node==null) {
      System.out.println(-1);
    }
    else {
      System.out.println(node.getFrequency());
      Node iterator=node.getSLL().getHead();
      while (iterator!=null){
        System.out.println(iterator.getFileName()+" "+iterator.getLineNumber());
        iterator=iterator.next;
      }
    }
  }

  public static void remove(String token, Index tree){
    if (tree.remove(token)) {
      return;
    }
    else
      System.out.println(0);
      //Dr.Sayed said that for errors in remove 0 should be printed. Refrence Al Salem
  }

}
