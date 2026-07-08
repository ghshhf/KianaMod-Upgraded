@echo off
chcp 65001 >nul
setlocal EnableDelayedExpansion

set SRC=E:\项目改变\KianaMod-m3\src
set MAIN=E:\项目改变\KianaMod-m3\src\main\java
set OUT=E:\项目改变\KianaMod-m3\classes
set JAVAC=G:\环境\bin\javac.exe

:: Build classpath
set CP=F:\steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
for %%j in (F:\steam\steamapps\workshop\content\646570\*\*.jar) do set CP=!CP!;%%j

echo Classpath built. Press any key to compile...
pause

:: Clean output
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

:: Compile
"%JAVAC%" -encoding UTF-8 --release 8 -proc:none -cp "%CP%" -d "%OUT%" ^
  "%SRC%\kianamod\modcore\ExampleMod.java" ^
  "%MAIN%\kianamod\card\FlamePursuit.java" ^
  "%MAIN%\kianamod\card\CrimsonCharge.java" ^
  "%MAIN%\kianamod\card\PassFlame.java" ^
  "%MAIN%\kianamod\card\SkyfireDraw.java" ^
  "%MAIN%\kianamod\card\EmberRekindle.java" ^
  "%MAIN%\kianamod\card\BurnOut.java" ^
  "%MAIN%\kianamod\card\ValkyrieOath.java" ^
  "%MAIN%\kianamod\card\FlameStorm.java" ^
  "%MAIN%\kianamod\card\Cinder.java" ^
  "%MAIN%\kianamod\card\PrairieFire.java" ^
  "%MAIN%\kianamod\card\FlameDragonBreak.java" ^
  "%MAIN%\kianamod\card\GuardianFlame.java" ^
  "%MAIN%\kianamod\card\VoidSlash.java" ^
  "%MAIN%\kianamod\card\SpaceFold.java" ^
  "%MAIN%\kianamod\card\QuantumTunnel.java" ^
  "%MAIN%\kianamod\card\GravitySingularity.java" ^
  "%MAIN%\kianamod\card\ImaginaryFill.java" ^
  "%MAIN%\kianamod\card\SubspaceWalker.java" ^
  "%MAIN%\kianamod\card\DimensionBanish.java" ^
  "%MAIN%\kianamod\card\StellarOrbit.java" ^
  "%MAIN%\kianamod\card\VoidDrain.java" ^
  "%MAIN%\kianamod\card\RiftWorld.java" ^
  "%MAIN%\kianamod\card\TimeShift.java" ^
  "%MAIN%\kianamod\card\SingularityBurst.java" ^
  "%MAIN%\kianamod\powers\BurnOutPower.java" ^
  "%MAIN%\kianamod\util\FlameCardUtils.java" ^
  "%MAIN%\kianamod\relics\HeavenFireMark.java" ^
  "%MAIN%\kianamod\relics\VoidRealm.java" ^
  "%MAIN%\kianamod\relics\FinalityOath.java" ^
  "%MAIN%\kianamod\relics\Moonlight.java" ^
  "%MAIN%\kianamod\relics\TruthKey.java" ^
  "%MAIN%\kianamod\relics\BloodRose.java" ^
  "%MAIN%\kianamod\relics\BlackWhiteFlower.java" ^
  "%MAIN%\kianamod\relics\EdenStar.java"

echo Exit code: %errorlevel%
if %errorlevel% neq 0 (echo COMPILATION FAILED) else (echo COMPILATION OK)
pause
