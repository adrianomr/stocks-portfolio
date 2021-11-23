vegeta attack -duration=3s -rate=10/s -targets=targets.txt | tee results.bin | vegeta report
vegeta plot results.bin > plot.html