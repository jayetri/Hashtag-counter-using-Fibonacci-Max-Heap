import java.util.Hashtable;
import java.io.*;


public class hashtagcounter {
    public static void main(String[] args) throws IOException {
        fibMaxHeap<String> fibbie = new fibMaxHeap<String>();

        BufferedReader Input_text = new BufferedReader(new FileReader(args[0])); //Reads the input

        Hashtable<String, fibMaxNode<String>> all_hashtags_diction = new Hashtable<>();


        PrintStream out ;

//If output file_name not mentioned in the command argument. It output to the console.
        if( args.length == 2 )
        {
            out = new PrintStream(new FileOutputStream( args[1] )); //If output file_name not mentioned in the command argument. It output to the console.
            System.setOut(out);
        }

        String[] arrs = null;
        int Numberofoutputs = 0;
        String hashtag_word = "";
        String line = "";
        int frequency_count = 0;


        while(true){
            line = Input_text.readLine();
            //read a line in the input file
            if (line.equals("stop")){
                break;
                //if "stop" break the loop
            }
            if (line.contains("#")){
                arrs = line.split(" ");
                hashtag_word = arrs[0].substring(1);
                frequency_count = Integer.parseInt(arrs[1]);


                if (all_hashtags_diction.containsKey(hashtag_word)){ //If key is present, it performs increase key in the fibonacci heap and increases the frequency value in the node.
                    fibMaxNode<String> renode = (fibMaxNode<String>) all_hashtags_diction.get(hashtag_word);
                    fibbie.FibMax_Key_Increase(renode, frequency_count);

                }else {
                    fibMaxNode<String> node = new fibMaxNode<String>(hashtag_word,frequency_count); //If key is not present, it insert the node in the fibonacci heap and insert the key or hashtag in the hashtable.
                    all_hashtags_diction.put(hashtag_word, node);
                    fibbie.FibMax_Insert(node, frequency_count);
                }

            }
            else {
                Numberofoutputs = Integer.parseInt(line); //If # and stop not present, it takes the integer value.
                @SuppressWarnings("unchecked")
                fibMaxNode<String>[] outNodeList = new fibMaxNode[Numberofoutputs]; //This represents the output node.


                //All the hashtags are written or displayed. During this process the maximum value of the fibonacci heap is removed the no. of times = the integer value present in the input file.
                for (int k = 0; k < Numberofoutputs; k++){
                    String outNodeData = fibbie.FibMax_maximum().value ;
                    Double outNodeKey = fibbie.FibMax_maximum().Heap_Key;
                    fibMaxNode<String> outNode = new fibMaxNode<String>(outNodeData,outNodeKey);
                    outNodeList[k] = outNode;
                    System.out.print( fibbie.FibMax_maximum().value) ;
                    if (k< Numberofoutputs -1){
                        System.out.print(",");
                    }
                    all_hashtags_diction.remove(fibbie.FibMax_maximum().value);
                    fibbie.Fib_RemoveMax();
                }
                System.out.print("\n");

                int j=0;
                while (j < Numberofoutputs) //All the keys and values of the hashtable- all_hashtags_diction is re-inserted. The nodes corresponding to the Fibonacci heap are also re-inserted.
                {
                    all_hashtags_diction.put(outNodeList[j].value, outNodeList[j]);
                    fibbie.FibMax_Insert(outNodeList[j], outNodeList[j].Heap_Key);
                    j= j+1;
                }


            }
        }



        Input_text.close(); //The input file is closed.
        //close the read file
        fibbie.FibMax_Clear(); //The fibonacci heap is cleared.


    }
}