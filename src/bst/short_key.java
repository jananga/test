/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bst;

/**
 *
 * @author ranjana
 */

import javax.swing.JOptionPane;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author ranjana
 */
public class short_key implements NativeKeyListener {

        bst_test bt = new bst_test() {

        public void nativeKeyPressed(NativeKeyEvent nke) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void nativeKeyReleased(NativeKeyEvent nke) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void nativeKeyTyped(NativeKeyEvent nke) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
        private int set=0;
        
    @Override
        public void nativeKeyPressed(NativeKeyEvent e) {

        }

    @Override
        public void nativeKeyReleased(NativeKeyEvent e) {
                    
                String s_key = NativeKeyEvent.getKeyText(e.getKeyCode());
                //JOptionPane.showMessageDialog(null, "Please Enter Password");
                if(s_key.equals("Ctrl") && set == 0)
                {
                //System.exit(0);
                    set = 1;
                }
                
                else if(set == 1 && s_key.equals("R"))
                {
                set = 2;
                //System.out.println(s_key);
                bt.run_f();
                }
                else if(set == 0 || set == 1)
                {
                JOptionPane.showMessageDialog(null, "Please Enter Password");
                set = 0;
                }

        }



    @Override
        public void nativeKeyTyped(NativeKeyEvent e) {
               
        }

        public static void main(String[] args) {
                run_exe();
        }

        public static void run_exe()
        {
        try {
                        GlobalScreen.registerNativeHook();
                         GlobalScreen.getInstance().addNativeKeyListener(new short_key());
                         JOptionPane.showMessageDialog(null, "Please Enter Password");
                }
                catch (NativeHookException ex) {
                    JOptionPane.showMessageDialog(null, "There was a problem registering the native hook"+ex.toString());

                        System.exit(1);
                }

        }

        public void run_form()
        {

        }

}
