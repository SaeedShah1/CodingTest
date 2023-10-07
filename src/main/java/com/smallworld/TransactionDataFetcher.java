package com.smallworld;

import com.smallworld.data.Transaction;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransactionDataFetcher {

    // Method to read Json and create a Transactions List
    public static List<Transaction> getDataSourceArray() throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        Object obj = new JSONParser().parse(new FileReader("D:\\coding test\\coding_test\\transactions.json"));
        JSONArray jArray = (JSONArray) obj;
        for (int i = 0; i < jArray.size(); i++) {
            Transaction transaction = new Transaction();
            JSONObject jsonObject = (JSONObject) jArray.get(i);
            transaction.setMtn((long) jsonObject.get("mtn"));
            transaction.setAmount((double) jsonObject.get("amount"));
            transaction.setBeneficiaryAge(Integer.parseInt(jsonObject.get("beneficiaryAge").toString()));
            transaction.setBeneficiaryFullName((String) jsonObject.get("beneficiaryFullName"));
            transaction.setIssueId(
                    jsonObject.get("issueId") != null ? Integer.parseInt(jsonObject.get("issueId").toString()) : null);
            transaction.setSenderFullName((String) jsonObject.get("senderFullName"));
            transaction.setSenderAge(Integer.parseInt(jsonObject.get("senderAge").toString()));
            transaction.setIssueSolved((boolean) jsonObject.get("issueSolved"));
            transaction.setIssueMessage((String) jsonObject.get("issueMessage"));
            transactionList.add(transaction);
        }
        return transactionList;
    }

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {

        try {
            double sumOfAllTransactions = 0;
            List<Transaction> transactions = getDataSourceArray();
            for (Transaction transaction : transactions) {
                sumOfAllTransactions = sumOfAllTransactions + transaction.getAmount();

            }

            return sumOfAllTransactions;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

        // throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified
     * client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        try {
            double sumOfSpecifiedClientAmount = 0;
            List<Transaction> transactions = getDataSourceArray();
            for (Transaction transaction : transactions) {
                if (transaction.getSenderFullName().equals(senderFullName)) {
                    sumOfSpecifiedClientAmount = sumOfSpecifiedClientAmount + transaction.getAmount();
                }

            }

            return sumOfSpecifiedClientAmount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }

        // throw new UnsupportedOperationException();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        try {

            List<Transaction> transactions = getDataSourceArray();
            double amountArray[] = new double[transactions.size()];
            double temp;

            for (int i = 0; i < transactions.size(); i++) {

                amountArray[i] = transactions.get(i).getAmount();

            }

            // logic to sort array
            for (int j = 0; j < amountArray.length; j++) {
                for (int k = j + 1; k < amountArray.length; k++) {
                    if (amountArray[j] > amountArray[k]) {
                        temp = amountArray[j];
                        amountArray[j] = amountArray[k];
                        amountArray[k] = temp;
                    }

                }

            }

            return amountArray[amountArray.length - 1];

            // Using StreamApi
            // return amountArray.aslist().stream().max(Double::compare).get();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        try {
            List<Transaction> transactions = getDataSourceArray();
            Set<String> senderOrRecieverUniqueList = new HashSet<>();
            Set<String> allSenderList = new HashSet<>();

            for (int i = 0; i < transactions.size(); i++) {

                // JSONObject iObject = (JSONObject) getDataSourceArray().get(i);
                allSenderList.add(transactions.get(i).getSenderFullName());
                for (int j = i + 1; j < transactions.size(); j++) {
                    // JSONObject jObject = (JSONObject) getDataSourceArray().get(j);
                    if (transactions.get(i).getSenderFullName().equals(transactions.get(j).getSenderFullName())) {
                        senderOrRecieverUniqueList.add(transactions.get(i).getSenderFullName());
                    }

                }

            }

            // System.out.println("Unique list is : " + uniqueList);

            return (allSenderList.size() - senderOrRecieverUniqueList.size());

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction
     * with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        try {
            for (Transaction transaction : getDataSourceArray()) {

                if ((transaction.isIssueSolved() == false) &&
                        (transaction.getSenderFullName().equals(clientFullName) ||
                                transaction.getBeneficiaryFullName().equals(clientFullName))) {
                    return true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        try {
            List<Transaction> transactions = getDataSourceArray();
            Map<String, Transaction> map = new HashMap<>();
            transactions.stream().sorted((p1, p2) -> p1.getBeneficiaryFullName().compareTo(p2.getBeneficiaryFullName()))
                    .forEach(t -> {

                        map.put(t.getBeneficiaryFullName(), t);
                    });

            return map;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        try {
            Set<Integer> issueSolvediDlist = new HashSet<>();
            List<Transaction> transactions = getDataSourceArray();
            for (Transaction transaction : transactions) {

                if (transaction.isIssueSolved() == false) {
                    issueSolvediDlist.add(transaction.getIssueId());
                }

            }

            return issueSolvediDlist;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        try {
            List<String> issueSolvedMessageslist = new ArrayList<>();
            List<Transaction> transactions = getDataSourceArray();
            for (Transaction transaction : transactions) {
                if (transaction.isIssueSolved() == true) {
                    issueSolvedMessageslist.add(transaction.getIssueMessage());
                }

            }

            return issueSolvedMessageslist;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount
     * descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        try {
            List<Transaction> transactions = getDataSourceArray();
            List<Transaction> topThreeAmountListDesc = new ArrayList<>();
            Transaction sorteAray[] = new Transaction[transactions.size()];
            Transaction tempTransaction;
            for (int i = 0; i < transactions.size(); i++) {
                sorteAray[i] = transactions.get(i);

            }

            for (int i = 0; i < sorteAray.length; i++) {
                for (int j = i + 1; j < sorteAray.length; j++) {
                    if (sorteAray[i].getAmount() > sorteAray[j].getAmount()) {
                        tempTransaction = sorteAray[i];
                        sorteAray[i] = sorteAray[j];
                        sorteAray[j] = tempTransaction;
                    }

                }
            }
            topThreeAmountListDesc.add(0, sorteAray[sorteAray.length - 1]);
            topThreeAmountListDesc.add(0, sorteAray[sorteAray.length - 2]);
            topThreeAmountListDesc.add(0, sorteAray[sorteAray.length - 3]);

            return topThreeAmountListDesc;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public String getTopSender() {
        try {
            List<Transaction> transactions = getDataSourceArray();
            Set<String> senderNames = new HashSet<>();
            Map<String, Double> maxSentMap = new HashMap<>();
            // Loop to get distinct names of all senders
            for (Transaction transaction : transactions) {
                senderNames.add(transaction.getSenderFullName());
            }

            // To create a map of sender with amount as values
            senderNames.stream().forEach(t -> {
                maxSentMap.put(t, 0.0);
            });

            // To sum the total of amounts against each name
            transactions.stream().forEach(t -> {
                senderNames.stream().forEach(name -> {
                    if (t.getSenderFullName().equals(name)) {
                        maxSentMap.put(name, maxSentMap.get(name) + t.getAmount());
                    }
                });
            });

            // Collections class is used to get the max value from map
            double max = Collections.max(maxSentMap.values());
            // Finally to return name and amount of highest sender
            System.out.println(maxSentMap + "\n");
            for (Map.Entry<String, Double> pair : maxSentMap.entrySet()) {
                if (pair.getValue() == max) {
                    return ("Sender with most sent amount is : " + pair.getKey() +
                            " with amount of : " + pair.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
