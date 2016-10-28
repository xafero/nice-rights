package com.xafero.nice.rights.rdf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

import org.apache.jena.ontology.OntTools;
import org.apache.jena.ontology.OntTools.Path;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import com.xafero.nice.rights.api.IExaminer;
import com.xafero.nice.rights.api.IId;
import com.xafero.nice.rights.api.IPrefix;

public class TripleExaminer implements IExaminer {

	private String lang;
	private String base;
	private Model model;

	public TripleExaminer(String file) throws IOException {
		lang = "N-TRIPLE";
		base = "r:";
		model = ModelFactory.createDefaultModel();
		try (FileInputStream in = new FileInputStream(file)) {
			model.read(in, base, lang);
		}
	}

	@Override
	public boolean isAllowed(IId<?> subject, IId<?> action, IId<?> object) {
		Resource start = model.createResource(getUri(subject));
		Property act = model.createProperty(getUri(action));
		Resource end = model.createResource(getUri(object));
		Predicate<Statement> filter = s -> true;
		Path path = OntTools.findShortestPath(model, start, end, filter);
		if (path != null)
			for (Statement stat : path)
				if (stat.getPredicate().equals(act))
					return true;
		return false;
	}

	private String getUri(IId<?> obj) {
		String type;
		if (obj instanceof IPrefix)
			type = ((IPrefix) obj).getPrefix();
		else
			type = obj.getClass().getSimpleName();
		String id = obj.getId().toString();
		return String.format("%s:%s", type, id);
	}

	@Override
	public void close() throws IOException {
		if (model == null || model.isClosed())
			return;
		model.close();
		model = null;
	}
}
