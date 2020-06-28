if [%1] == [] goto :missingParameter
if [%2] == [] goto :missingParameter

set releaseVersion=%1
set nextVersion=%2

call mvnw versions:set -DgenerateBackupPoms=false -DnewVersion=%releaseVersion% || goto :error
call mvnw scm:checkin -DpushChanges=false "-Dmessage=Update version to %releaseVersion%" || goto :error
call mvnw scm:tag -DpushChanges=false -Dtag=%releaseVersion% || goto :error
call mvnw versions:set -DgenerateBackupPoms=false -DnewVersion=%nextVersion% || goto :error
call mvnw scm:checkin -DpushChanges=false "-Dmessage=Update version to %nextVersion%" || goto :error
call mvnw scm:checkin || goto :error

exit /b 0

:missingParameter
echo "Usage: release <releaseVersion> <nextVersion>"
exit /b 1

:error
exit /b %errorlevel%
