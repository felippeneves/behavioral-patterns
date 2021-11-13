package br.com.felippeneves.chain_of_responsability.process.service;

import br.com.felippeneves.chain_of_responsability.process.steps.ProcessStep;
import br.com.felippeneves.chain_of_responsability.process.steps.SaveUser;
import br.com.felippeneves.chain_of_responsability.process.steps.ValidateMandatoryField;

public class ServicesCatalog {
	
	public static ProcessStep saveUserProcess = buildChain(
			new ValidateMandatoryField("name"),
			new ValidateMandatoryField("email"),
			new ValidateMandatoryField("password"),
			new SaveUser());
	
	private static ProcessStep buildChain(ProcessStep... steps) {
		for(int index = 0; index < steps.length -1; index++) {
			ProcessStep currentProcess = steps[index];
			currentProcess.setNextStep(steps[index + 1]);
		}
		return steps[0];
	}
}