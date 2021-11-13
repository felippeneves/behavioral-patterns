package br.com.felippeneves.chain_of_responsability.processplus.service;

import br.com.felippeneves.chain_of_responsability.processplus.steps.CheckPassword;
import br.com.felippeneves.chain_of_responsability.processplus.steps.CreateMap;
import br.com.felippeneves.chain_of_responsability.processplus.steps.GetUserByEmail;
import br.com.felippeneves.chain_of_responsability.processplus.steps.GetUserToken;
import br.com.felippeneves.chain_of_responsability.processplus.steps.GetUsers;
import br.com.felippeneves.chain_of_responsability.processplus.steps.ProcessStep;
import br.com.felippeneves.chain_of_responsability.processplus.steps.SaveUser;
import br.com.felippeneves.chain_of_responsability.processplus.steps.ValidateMandatoryField;
import br.com.felippeneves.chain_of_responsability.processplus.steps.ValidateToken;

public class ServicesCatalog {
	
	public static ProcessStep saveUserProcess = buildChain(
			new CreateMap("input"),
			new ValidateMandatoryField("input.name"),
			new ValidateMandatoryField("input.email"),
			new ValidateMandatoryField("input.password"),
			new SaveUser());
	
	public static ProcessStep loginProcess = buildChain(
			new CreateMap("input"),
			new ValidateMandatoryField("input.email"),
			new ValidateMandatoryField("input.password"),
			new GetUserByEmail(),
			new CheckPassword(),
			new GetUserToken());
	
	public static ProcessStep getUsersProcess = buildChain(
			new ValidateToken(),
			new GetUsers());
	
	private static ProcessStep buildChain(ProcessStep... steps) {
		for(int index = 0; index < steps.length -1; index++) {
			ProcessStep currentProcess = steps[index];
			currentProcess.setNextStep(steps[index + 1]);
		}
		return steps[0];
	}
}