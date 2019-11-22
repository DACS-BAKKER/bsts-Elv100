import edu.princeton.cs.algs4.StdOut;

/*
 * Elven Shum
 * Animal Search Tree, specifically fore Easy Animal Dev
 */

public class AnimalST<T>{
    public STNode root;
    public STNode curr;  //current position

    public AnimalST(){
        root = new STNode();
    }

    public AnimalST(String data){
        root = new STNode(data);
    }

    public void init(){
        curr = root;
    }


    public void travLeft(){
        if (curr.left == null){
            StdOut.println("Oh no! You've Ltraversed to nothing!");
            return;
        }
        curr = curr.left;
    }
    public void travRight(){
        if (curr.right == null){
         StdOut.println("Oh no! You've Rtraversed to nothing!");
         return;
        }
        curr = curr.right;
    }

    public boolean isLeaf(){
        return (curr.right==null && curr.left==null);
    }

//    public void changeCurr(T data){
//        curr.element = data;
//    }

    public void addRight(String data){
        if(curr.right!=null){
            StdOut.println("already Has a right!");
        } else {
            curr.right = new STNode(data);
        }
    }

    public void addLeft(String data){
        if(curr.left!=null){
            StdOut.println("already Has a left!");
        } else {
            curr.left = new STNode(data);
        }
    }




    static class STNode {
        public String element;
        public STNode left, right;

        public STNode(String key, String element) {
            this.element = element;

            this.left = this.right = null;
        }

        public STNode(String element) {
            this.element = element;
            this.left = this.right = null;
        }

        public STNode(){
            this.element = null;
            this.left = this.right = null;
        }
    }
}
