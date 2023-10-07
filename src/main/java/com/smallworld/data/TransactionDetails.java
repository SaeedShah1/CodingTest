package com.smallworld.data;

import java.util.Scanner;

import com.smallworld.TransactionDataFetcher;

public class TransactionDetails {


    static TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Get Total Transactions
        //  System.out.println("Sum of amount of all transactions is : " + transactionDataFetcher.getTotalTransactionAmount());

        //Get sum of amount of transaaction by name
    //     System.out.println("\nEnter Name of Sender to get Amount");
    //     String senderName = sc.nextLine();

    //     if(transactionDataFetcher.getTotalTransactionAmountSentBy(senderName) != 0){
    //     System.out.println("\nSum of amount of all transactions is : " + 
    //      transactionDataFetcher.getTotalTransactionAmountSentBy(senderName));
    // }
    //     else{
    //         System.out.println("\nNo sender Name Found");
    //     }

    //  Maximum amount in data
    //   System.out.println("Maximum amount in data : " + transactionDataFetcher.getMaxTransactionAmount()); 
       

//  System.out.println("Unique sender or receiver " + transactionDataFetcher.countUniqueClients());


// hasOpenComplianceIssues
// boolean hasOpenCompliance = transactionDataFetcher.hasOpenComplianceIssues(sc.nextLine());
// if(hasOpenCompliance){
//     System.out.println("Yes it has open issues " + hasOpenCompliance);
// }else{
// System.out.println("No issue " + hasOpenCompliance); 
// }


// Get All solved Issues Messages
// System.out.println(transactionDataFetcher.getAllSolvedIssueMessages());

// Get all un solved issues IDs
// System.out.println(transactionDataFetcher.getUnsolvedIssueIds());



// transactionDataFetcher.getTransactionsByBeneficiaryName();

//Top 3 Transactions
// System.out.println("Tope Three Amount Transactions are : " );
//  transactionDataFetcher.getTop3TransactionsByAmount().stream().forEach((transaaction)->{
//  System.out.println("Amount : " + transaaction.getAmount() + "\nBenef Name :" +
//   transaaction.getBeneficiaryFullName() +"\nSenclearder Name:" +transaaction.getSenderFullName()+ "\n" 
//    );
//  });


// Returns the senderFullName of the sender with the most total sent amount
//  System.out.println(transactionDataFetcher.getTopSender());


// Returns all transactions indexed by beneficiary name
 System.out.println(transactionDataFetcher.getTransactionsByBeneficiaryName());
        
    }
    
}
