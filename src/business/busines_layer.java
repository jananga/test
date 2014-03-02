/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import DB_layer.db_layer;

/**
 *
 * @author ranjana
 */
public class busines_layer {

    db_layer bl = new db_layer();

    public String test()
    {
    String val = bl.testDB();
    return  val;
    }
}
