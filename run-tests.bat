@echo off
setlocal

rem Load .env if present
if exist "%~dp0.env" (
  for /f "usebackq tokens=1,* delims==" %%A in ("%~dp0.env") do (
    set "%%A=%%B"
  )
)

mvn clean test -Dbrowser=chrome -Dheadless=true
