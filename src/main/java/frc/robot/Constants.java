// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.utils.*;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    public static final double kDirectionSlewRate = 1.2; // radians per second
    public static final double kMagnitudeSlewRate = 1.8; // percent per second (1 = 100%)
    public static final double kRotationalSlewRate = 2.0; // percent per second (1 = 100%)

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(24.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(24.5);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // SPARK MAX CAN IDs
    public static final int kFrontRightTurningCanId = 1;
    public static final int kFrontRightDrivingCanId = 2;
    public static final int kFrontLeftTurningCanId = 3;
    public static final int kFrontLeftDrivingCanId = 4;
    public static final int kRearLeftTurningCanId = 5;
    public static final int kRearLeftDrivingCanId = 6;
    public static final int kRearRightTurningCanId = 7;
    public static final int kRearRightDrivingCanId = 8;

    public static final boolean kGyroReversed = false;
  }

  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T, 13T, or 14T.
    // This changes the drive speed of the module (a pinion gear with more teeth will result in a
    // robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 13;

    // Invert the turning encoder, since the output shaft rotates in the opposite direction of
    // the steering motor in the MAXSwerve Module.
    public static final boolean kTurningEncoderInverted = true;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15 teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;

    public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction) / 60.0; // meters per second

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

    public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
    public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians

    public static final double kDrivingP = 0.04;
    public static final double kDrivingI = 0;
    public static final double kDrivingD = 0;
    public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
    public static final double kDrivingMinOutput = -1;
    public static final double kDrivingMaxOutput = 1;

    public static final double kTurningP = 1;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningFF = 0;
    public static final double kTurningMinOutput = -1;
    public static final double kTurningMaxOutput = 1;

    public static final IdleMode kDrivingMotorIdleMode = IdleMode.kBrake;
    public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

    public static final int kDrivingMotorCurrentLimit = 50; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps
  }

  public static final class ControllerConstants {
    public static final int kNavigatorPort = 0;
    public static final int kOperatorPort = 1;
    public static final double kDriveDeadband = 0.1;
  }

  public static final class ArmConstants {
    public static final int kArmMotorLeaderPort = 9;
    public static final int kArmMotorFollowerPort = 10;
    public static final double kPower = 0.4;
    public static final double kP = 0.04;
    public static final double kD = 0.4;
    public static final double kFF = 0; 
    public static final double kMaxOutput = 1;
    public static final double kMinOutput = -1;

    public static final int kSpoolMotorPort = 11;
    public static final double kSpoolMotorForwardLimit = 0.227;
    public static final double kSpoolMotorReverseLimit = 0;
    public static final double kInitialSpoolPosition = 0;

    public enum armPosition {
      START, INTAKE, LOW, HIGH, EXTRA_HIGH;
    }
  }

  public static final class GrabberConstants {
    public static final int kGrabberSolenoidPorts[] = {2, 3, 4, 5};
    public static final int kIntakeVoltage = 6;
    public static Integer isStart = 0;
  }

  public static final class DrivetrainConstants {
    public static final int kLeftSRXDrivePort = 51;
    public static final int kLeftSPXDrivePort = 02;
    public static final int kRightSRXDrivePort = 20;
    public static final int kRightSPXDrivePort = 21;

    public static final double kDriveForwardMultiplier = 1;
    public static final double kDriveTurnMultiplier = 1;

    public static final double kLinearKS = 1.5819;
    public static final double kLinearKV = 2.9238;
    public static final double kLinearKA = 1.6274;
    public static final double kAngularKS = 1.9351;
    public static final double kAngularKV = 3.4356;
    public static final double kAngularKA = 1.2996;

    public static final double kP = 1.5551;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
    public static final double kWheelDiameterInches = 6;
    public static final double kWheelCircumferenceMeters = Units.inchesToMeters(6 * Math.PI);
    public static final double kTrackwidth = 0.57591;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidth);
    public static final double kCimToWheelGearing = 10.71;

    public static final double kAngularKP = .1;
    public static final double kAngularKD = .0;

    public static final double kMaxTurnRate = 100;
    public static final double kMaxTurnAccel = 10;
    public static final double kDegreeTolerance = 0.75;
    public static final double kTurnRateTolerance = 1.5;

    public static final double kAutoMaxSpeed = 1;
    public static final double kAutoMaxAccel = 1;

    public static final double kAutoTime = 2.5;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 5676;
  }

  public static final class LimelightConstants {
    public static final double kLensHeightMeters = Units.inchesToMeters(27.299450);
    public static final double kMountAngleDegrees = 50.000000;
    public static final double kTargetHeightMeters = Units.inchesToMeters(104.0 - 1.0);

    public static final double kLimelightP = 0.45;
    public static final double kLimelightD = 0.0;

    public static InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> kShooterRPMMap =
        new InterpolatingTreeMap<>();

    static {
      kShooterRPMMap.put(new InterpolatingDouble(0.97), new InterpolatingDouble(1450.0));
      kShooterRPMMap.put(new InterpolatingDouble(1.72), new InterpolatingDouble(1600.0));
      kShooterRPMMap.put(new InterpolatingDouble(2.04), new InterpolatingDouble(1700.0));
      kShooterRPMMap.put(new InterpolatingDouble(2.60), new InterpolatingDouble(1800.0));
    }

    public static InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> kHoodMap =
        new InterpolatingTreeMap<>();

    static {
      kHoodMap.put(new InterpolatingDouble(0.97), new InterpolatingDouble(8.0));
      kHoodMap.put(new InterpolatingDouble(1.72), new InterpolatingDouble(20.0));
      kHoodMap.put(new InterpolatingDouble(2.04), new InterpolatingDouble(19.7));
      kHoodMap.put(new InterpolatingDouble(2.66), new InterpolatingDouble(24.6));
    }
  }
}
