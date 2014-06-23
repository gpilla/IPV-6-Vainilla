package com.uqbar.vainilla;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;

public abstract class GameComponentRule<ComponentType extends GameComponent<GameScene>> {

	abstract public boolean mustApply(ComponentType component, DeltaState deltaState);

	abstract public void apply(ComponentType component, DeltaState deltaState);
		
}
