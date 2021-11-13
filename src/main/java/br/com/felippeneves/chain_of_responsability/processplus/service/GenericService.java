package br.com.felippeneves.chain_of_responsability.processplus.service;

import br.com.felippeneves.chain_of_responsability.processplus.steps.ProcessStep;

public class GenericService {
	
	public static Object run(ProcessStep startProcess, ProcessContext initialContext) {
		try {
			ProcessContext result = startProcess.execute(initialContext);
			return result.getProcessResult();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
}