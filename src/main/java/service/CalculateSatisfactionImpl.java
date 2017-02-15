package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CalculateSatisfactionImpl {

	@Autowired
	Environment env;

	public void getSatisfaction() {

		String filePath = env.getProperty("filePath");
		try {
			// read file from the path entered in properties
			Scanner sc = new Scanner(new File(filePath));

			int timeLimit = sc.nextInt(); // timeLimit
			int noOfItem = sc.nextInt(); // noOfItems

			processFile(timeLimit, noOfItem, sc);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processFile(int timeLimit, int n, Scanner sc) {
		int satisfactionValue[] = new int[n];
		int timeTaken[] = new int[n];
		int i = 0, j = 0;
		// read all satisfaction values and timeLimits
		while (sc.hasNext()) {
			satisfactionValue[i] = sc.nextInt();
			timeTaken[j] = sc.nextInt();
			i++;
			j++;
		}
		int knapSack[][] = new int[n + 1][timeLimit + 1];

		// Build table knapSack[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (int tl = 0; tl <= timeLimit; tl++) {
				if (i == 0 || tl == 0)
					knapSack[i][tl] = 0;
				else if (timeTaken[i - 1] <= tl)
					knapSack[i][tl] = Math.max(satisfactionValue[i - 1]
							+ knapSack[i - 1][tl - timeTaken[i - 1]],
							knapSack[i - 1][tl]);
				else
					knapSack[i][tl] = knapSack[i - 1][tl];
			}
		}

		System.out.println("Maximum satisfaction Gordon can get is :"
				+ knapSack[n][timeLimit]);
	}
}
