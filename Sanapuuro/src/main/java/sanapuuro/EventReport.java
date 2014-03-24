/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

/**
 *
 * @author skaipio
 */
public class EventReport {
    public final boolean succeeded;
    public final String message;

    public EventReport(boolean succeeded, String message) {
        this.succeeded = succeeded;
        this.message = message;
    }
}
