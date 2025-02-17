// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightManager;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LimelightAim extends PIDCommand {
  /** Creates a new LimelightAim. */
  public LimelightAim(LimelightManager limelight, DriveSubsystem driveSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(LimelightConstants.kLimelightP, 0, LimelightConstants.kLimelightD),
        // This should return the measurement
        () -> limelight.getHorizontalErrorDegrees(),
        // This should return the setpoint (can also be a constant)
        () -> 0.0,
        // This uses the output
        output -> {
          driveSubsystem.setInvertedX(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);

    // Configure additional PID options by calling `getController` here.

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
