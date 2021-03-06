// Modified with permission by Anthony Frazier
// email: anthonyf@email.sc.edu
// CSCE311 Spring 17
// Project Two: ThreadCB
// Objective: Yes.
package osp.Threads;

import osp.IFLModules.*;
import osp.Utilities.*;
import osp.Hardware.*;

/**    
       The timer interrupt handler.  This class is called upon to
       handle timer interrupts.

       @OSPProject Threads
*/
public class TimerInterruptHandler extends IflTimerInterruptHandler
{
    /**
       This basically only needs to reset the times and dispatch
       another process.

       @OSPProject Threads
    */
    public void do_handleInterrupt()
    {
        ThreadCB.dispatch();
    }

}