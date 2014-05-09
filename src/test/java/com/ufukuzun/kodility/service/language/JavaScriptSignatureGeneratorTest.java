package com.ufukuzun.kodility.service.language;

import com.ufukuzun.kodility.domain.challenge.Challenge;
import com.ufukuzun.kodility.enums.DataType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JavaScriptSignatureGeneratorTest {

    private JavaScriptSignatureGenerator signatureGenerator = new JavaScriptSignatureGenerator();

    @Test
    public void shouldGeneratePatternWithoutParameters() {
        Challenge challenge = new Challenge();

        assertThat(signatureGenerator.generate(challenge), equalTo("function solution() {\n\n}"));
    }

    @Test
    public void shouldGeneratePatternWithMultipleParameters() {
        Challenge challenge = new Challenge();

        List<DataType> inputTypes = new ArrayList<>();
        inputTypes.add(DataType.Integer);
        inputTypes.add(DataType.Integer);
        challenge.setInputTypes(inputTypes);
        challenge.setOutputType(DataType.Integer);

        assertThat(signatureGenerator.generate(challenge), equalTo("function solution(a, b) {\n\n}"));
    }

}
