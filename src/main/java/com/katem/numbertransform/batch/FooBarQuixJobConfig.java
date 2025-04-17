package com.katem.numbertransform.batch;

import com.katem.numbertransform.service.TransformerService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FooBarQuixJobConfig {private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final TransformerService transformerService;

    public FooBarQuixJobConfig(final JobRepository jobRepository, final PlatformTransactionManager transactionManager,TransformerService transformerService) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.transformerService = transformerService;
    }

    @Bean
    public Job transformJob() {
        return new JobBuilder("transformJob", jobRepository)
                .start(transformStep())
                .build();
    }

    @Bean
    public Step transformStep() {
        return new StepBuilder("transformStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<String> reader() {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("input.txt"));
        reader.setLineMapper((line, lineNumber) -> line);
        return reader;
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return input -> {
            int number = Integer.parseInt(input);
            return number + " \"" + transformerService.transform(number) + "\"";
        };
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/output.txt"));
        writer.setLineAggregator(new PassThroughLineAggregator<>());
        return writer;
    }
}