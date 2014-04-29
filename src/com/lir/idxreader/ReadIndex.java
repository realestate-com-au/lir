package com.lir.idxreader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;

public class ReadIndex {

	private File m_indexDir;
	private IndexReader m_indexReader;

	public ReadIndex(String indexDirPath) {
		super();
		this.m_indexDir = new File(indexDirPath);
		try {
			this.m_indexReader = DirectoryReader.open(new NIOFSDirectory(m_indexDir));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Prints all the FieldNames and their Values for a given document.
	 * 
	 * @param doc
	 */
	public void printAllFieldsOfADocument(Document doc) {
		System.out.println("###################################");
		// System.out.println("Details of the document "+doc.toString());
		List<IndexableField> fields = doc.getFields();
		Iterator<IndexableField> itr = fields.iterator();
		while (itr.hasNext()) {
			IndexableField field = (IndexableField) itr.next();
			System.out.println(field.name() + " : " + field.stringValue());
		}
	}

	/**
	 * Searches the Index with provided fieldName and fieldValue combination.
	 * 
	 * @param fieldName
	 * @param fieldValue
	 */
	public void searchIndex(String fieldName, String fieldValue) {
		Document doc = null;
		for (int i = 0; i < m_indexReader.numDocs(); i++) {
			try {
				doc = m_indexReader.document(i);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			// System.out.println(doc.getField(fieldName));
			if (doc.getField(fieldName) != null) {
				if (doc.getField(fieldName).toString().contains(fieldValue)) {
					printAllFieldsOfADocument(doc);
				}
			}
		}
		closeReader();
	}

	/**
	 * 
	 */
	public void listAllDocumentsDetails() {
		try {
			System.out.println("Reading " + m_indexReader.numDocs()
					+ " documents of the Index");
			Document d = null;
			for (int i = 0; i < m_indexReader.numDocs(); i++) {
				d = m_indexReader.document(i);
				printAllFieldsOfADocument(d);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		closeReader();
	}
	
	public void closeReader(){
		try {
			this.m_indexReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
