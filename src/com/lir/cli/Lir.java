package com.lir.cli;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.lir.idxreader.ReadIndex;

public class Lir {

	private Options m_options;
	private CommandLine m_cmd;
	private ReadIndex readIndex;

	public Lir() {
		super();
	}

	public static void main(String args[]) {
		Lir lir = new Lir();
		lir.createOptions();
		lir.m_cmd = lir.parseCommands(args);

		lir.readIndex = new ReadIndex(lir.m_cmd.getOptionValue("indexpath"));

		if (lir.m_cmd.hasOption("search")) {
			if (!lir.m_cmd.getOptionValue("search").contains(",")) {
				System.out
						.println("You should provide fieldname,fieldvalue while using search option");
				lir.usage();
			}
			String[] strs = lir.m_cmd.getOptionValue("search").split(",");
			lir.readIndex.searchIndex(strs[0], strs[1]);
		} else if (lir.m_cmd.hasOption("listall")) {
			lir.readIndex.listAllDocumentsDetails();
		} else {
			System.out
					.println("You should use either -listall option or -search option");
			lir.usage();
		}
	}

	private void createOptions() {
		m_options = new Options();

		Option indexPath = new Option("i", "indexpath", true,
				"path to the lucene index directory");
		indexPath.setRequired(true);
		indexPath.setArgs(1);
		indexPath.setArgName("pathToIdxDir");
		m_options.addOption(indexPath);

		Option listall = new Option("listall", false,
				"lists all the documents of the lucene index");
		m_options.addOption(listall);

		Option search = new Option("search", true,
				"search the index based on fieldname and fieldvalue combination");
		search.setArgs(2);
		search.setArgName("fieldName,fieldValue");
		m_options.addOption(search);
	}

	private CommandLine parseCommands(String[] args) {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(m_options, args);
		} catch (ParseException pe) {
			System.out.println(pe.getMessage());
			usage();
		}
		return cmd;
	}

	private void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("lir -indexpath <-listall | -search>", m_options);
		System.exit(0);
	}
}
