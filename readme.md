`if (true) {
    chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
    .put("status", "passed");
    System.out.println("test1 is passed");
} else {
    chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
    .put("status", "failed");
    System.out.println("test1 is failed");
}`