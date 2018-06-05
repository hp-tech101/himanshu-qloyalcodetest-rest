# himanshu-qloyalcodetest-rest
## 1 - To Run the project locally: <br/>

#### Pre-requisite: jdk8 and Maven cli is installed and configured <br/>

&nbsp;a. git clone git@github.com:hp-tech101/himanshu-qloyalcodetest-rest.git <br/>
&nbsp;b. Passphrase: Success17$$18 <br/>
&nbsp;c. cd himanshu-qloyalcodetest-rest <br/>
&nbsp;d. Run following commands in that order from command prompt: <br/>
&nbsp;&nbsp;&nbsp;&nbsp;d.1 command 1 -> mvn clean <br/>
&nbsp;&nbsp;&nbsp;&nbsp;d.2 command 2 -> mvn compile <br/>
&nbsp;&nbsp;&nbsp;&nbsp;d.3 command 3 -> mvn test <br/>
&nbsp;e. Report is generated in artefacts/reports/ExecutionReport.html <br/>

## 2 - Execution from CircleCI <br/>

&nbsp;a. To view the circleCI build login to circleci.com (via github) using the following credentials <br/>
            username: himanshu.tech101@gmail.com <br/>
            password: Eminence17$$18 <br/>
            
&nbsp;b. To view the report from circleCI build, under each build goto the "Artifacts" tab and browse to <br/>

        ### Container0/home/circleci/project/artefacts/reports/ExecutionReport.html  
<br/>
&nbsp;c. Also, the build has been scheduled to run every hour in config.yml and build notifications can be seen on a slack channel <br/>
         ### Slack Channel details <br/>
         #### url : https://codetest-group.slack.com/ <br/>
         #### channel : qloyalcodetest-ci <br/>
         #### username: himanshu.tech101@gmail.com <br/>
         #### password: Eminence17$$18 <br/>

## 3 - To view the defects <br/>
Go to https://dashboard.leantesting.com/en/projects/qloyalcodetest/34839/bug-tracker#3 <br/>
username: himanshutech101 <br/>
password: Success17$$18

## Note
## Post request is failing because of issue with Content-Type (pending debugging) because of which build on CircleCI is failing
