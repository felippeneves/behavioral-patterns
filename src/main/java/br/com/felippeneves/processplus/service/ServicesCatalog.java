package br.com.felippeneves.processplus.service;

import br.com.felippeneves.processplus.steps.CheckPassword;
import br.com.felippeneves.processplus.steps.CreateMap;
import br.com.felippeneves.processplus.steps.GetUserByEmail;
import br.com.felippeneves.processplus.steps.GetUserToken;
import br.com.felippeneves.processplus.steps.GetUsers;
import br.com.felippeneves.processplus.steps.ProcessStep;
import br.com.felippeneves.processplus.steps.SaveUser;
import br.com.felippeneves.processplus.steps.ValidateMandatoryField;
import br.com.felippeneves.processplus.steps.ValidateToken;

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