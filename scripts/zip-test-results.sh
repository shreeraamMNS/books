set -e

mkdir test-artifacts
cp build/test-results/test/TEST-*.xml test-artifacts/
cd test-artifacts
zip test-results.zip TEST-*.xml
