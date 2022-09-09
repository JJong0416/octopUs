package com.octopus.config;

import com.octopus.api.service.MissionDetailsService;
import com.octopus.domain.Mission;
import com.octopus.domain.type.MissionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
<<<<<<< HEAD
=======
import java.time.ZoneId;
import java.time.ZonedDateTime;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MissionDetailsService missionDetailsService;

    // exampleJob 생성
    @Bean
    public Job changeMissionStatusJob() throws Exception {
        return jobBuilderFactory.get("changeMissionStatusJob")
                .start(toOngoingMissionStatusStep())
                .next(toCloseMissionStatusStep()).build();
    }

    // exampleStep 생성
    @Bean
    @JobScope
    public Step toOngoingMissionStatusStep() throws Exception {
        return stepBuilderFactory.get("toOngoingMissionStatusStep")
                .<Mission, Mission>chunk(10)
                .reader(toOngoingReader(null))
                .processor(toOngoingProcessor(null))
                .writer(toOngoingWriter(null))
                .build();
    }


    @Bean
    @JobScope
    public Step toCloseMissionStatusStep() throws Exception {
        return stepBuilderFactory.get("toCloseMissionStatusStep")
                .<Mission, Mission>chunk(10)
                .reader(toCloseReader(null))
                .processor(toCloseProcessor(null))
                .writer(toCloseWriter(null))
                .build();

    }
    @Bean
    @StepScope
    public JpaPagingItemReader<Mission> toOngoingReader(@Value("#{jobParameters[requestDate]}")  String requestDate) throws Exception {
        log.info("==> reader value : " + requestDate);

        Map<String, Object> parameterValues = new HashMap<>();
<<<<<<< HEAD
        parameterValues.put("nowDateTime", LocalDateTime.now());
=======
        parameterValues.put("nowDateTime", ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95

        return new JpaPagingItemReaderBuilder<Mission>()
                .pageSize(10)
                .parameterValues(parameterValues)
                .queryString("SELECT m FROM Mission m WHERE m.missionNo in (select mt.mission from MissionTime mt where mt.missionTimeStartTime <= :nowDateTime)")
                .entityManagerFactory(entityManagerFactory)
                .name("JpaPagingItemReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Mission, Mission> toOngoingProcessor(@Value("#{jobParameters[requestDate]}")  String requestDate){
        return new ItemProcessor<Mission, Mission>() {
            @Override
            public Mission process(Mission mission) throws Exception {

                log.info("==> processor Mission : " + mission);
                log.info("==> processor value : " + requestDate);
                if(mission.getMissionStatus() == MissionStatus.OPEN){
                    mission.updateMissionStatus(MissionStatus.ONGOING);
                }


                return mission;
            }
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Mission> toOngoingWriter(@Value("#{jobParameters[requestDate]}")  String requestDate){
        log.info("==> writer value : " + requestDate);

        return new JpaItemWriterBuilder<Mission>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    // toClose
    @Bean
    @StepScope
    public JpaPagingItemReader<Mission> toCloseReader(@Value("#{jobParameters[requestDate]}")  String requestDate) throws Exception {
        log.info("==> reader value : " + requestDate);

        Map<String, Object> parameterValues = new HashMap<>();
<<<<<<< HEAD
        parameterValues.put("nowDateTime", LocalDateTime.now());
=======
        parameterValues.put("nowDateTime", ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95


        return new JpaPagingItemReaderBuilder<Mission>()
                .pageSize(10)
                .parameterValues(parameterValues)
                .queryString("SELECT m FROM Mission m WHERE m.missionNo in (select mt.mission from MissionTime mt where mt.missionTimeEndTime <= :nowDateTime)")
                .entityManagerFactory(entityManagerFactory)
                .name("JpaPagingItemReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Mission, Mission> toCloseProcessor(@Value("#{jobParameters[requestDate]}")  String requestDate){
        return new ItemProcessor<Mission, Mission>() {
            @Override
            public Mission process(Mission mission) throws Exception {

                log.info("==> processor Mission : " + mission);
                log.info("==> processor value : " + requestDate);

                if(mission.getMissionStatus() == MissionStatus.ONGOING){
                    mission.updateMissionStatus(MissionStatus.CLOSE);
                    missionDetailsService.rewardDistribution(mission.getMissionNo());
                }

                return mission;
            }
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Mission> toCloseWriter(@Value("#{jobParameters[requestDate]}")  String requestDate){
        log.info("==> writer value : " + requestDate);

        return new JpaItemWriterBuilder<Mission>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
