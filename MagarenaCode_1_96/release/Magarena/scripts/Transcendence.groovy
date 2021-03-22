[
    new IfPlayerWouldLoseTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final LoseGameAction loseAct) {
            if (permanent.isController(loseAct.getPlayer()) && loseAct.getReason() == LoseGameAction.LIFE_REASON) {
                loseAct.setPlayer(MagicPlayer.NONE);
            }
            return MagicEvent.NONE;
        }
    },
    new LifeIsLostTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicLifeChangeTriggerData lifeChange) {
            final int amount = lifeChange.amount;
            return permanent.isFriend(lifeChange.player) ?
                new MagicEvent(
                    permanent,
                    amount,
                    this,
                    "PN gains 2 life for each 1 life he or she lost. (${amount})"
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new ChangeLifeAction(event.getPlayer(), event.getRefInt()*2));
        }
    }
]
