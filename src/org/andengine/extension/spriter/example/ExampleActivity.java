/*
 * Spriter Extension for AndEngine
 *
 * Copyright (c) 2012 Arturo Gutiérrez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package org.andengine.extension.spriter.example;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.spriter.SpriterEntity;
import org.andengine.extension.spriter.SpriterLoader;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.DisplayMetrics;

public class ExampleActivity extends SimpleBaseGameActivity {

    private Scene mMainScene;
    private SpriterEntity mSprite;
    
    @Override
    public EngineOptions onCreateEngineOptions() {
        // Create Metrics object
        DisplayMetrics metrics = new DisplayMetrics();
        // Fill metrics object
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // Create canera
        Camera camera = new Camera(0, 0, metrics.widthPixels, metrics.heightPixels);

        // Return engine options
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(camera.getWidth() / camera.getHeight()), camera);
    }

    @Override
    protected void onCreateResources() {
        // Create sprite
        mSprite = SpriterLoader.createSpriterFrom(this, "example.scml");
        // Load textures
        mSprite.loadResources(this, getTextureManager(), getVertexBufferObjectManager());
    }

    @Override
    protected Scene onCreateScene() {
        // Create empty Scene
        mMainScene = new Scene();
        
        if (mSprite != null) {
            // Move Sprite
            mSprite.setPosition(mEngine.getCamera().getWidth() / 2, mEngine.getCamera().getHeight() / 2);
            mSprite.setAnimation(1);
            // Attach sprite
            mMainScene.attachChild(mSprite);
        }
        
        
        return mMainScene;
    }

}
