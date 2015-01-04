/*
 * Copyright (C) 2014 Michell Bak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aaaliua.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;




public class ViewUtils {

    private ViewUtils() {} // No instantiation

    public static int getNavigationDrawerWidth(Context context) {
        int drawerWidth;

        // The navigation drawer should have a width equal to
        // the screen width minus the Toolbar height - at least
        // on mobile devices. Tablets are accounted for below.
        int toolbarHeight = Lib.getActionBarHeight(context);

        // Get the display size
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        // Get the smallest number
        int smallestDisplayWidth = Math.min(size.x, size.y);

        // Calculate the drawer width
        drawerWidth = smallestDisplayWidth - toolbarHeight;

        // Make sure that the calculated drawer width
        // isn't greater than the max width, i.e.
        // 5 times the standard increment (56dp for
        // mobile or 64dp for tablets).
        int maxWidth = Lib.convertDpToPixels(context, 5 * (Lib.isTablet(context) ? 64 : 56));

        if (drawerWidth > maxWidth)
            drawerWidth = maxWidth;

        return drawerWidth;
    }
}
