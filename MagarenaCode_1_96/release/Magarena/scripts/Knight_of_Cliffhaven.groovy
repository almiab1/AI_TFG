[
    new MagicStatic(MagicLayer.SetPT) {
        @Override
        public void modPowerToughness(final MagicPermanent source,final MagicPermanent permanent,final MagicPowerToughness pt) {
            final int level = permanent.getCounters(MagicCounterType.Level);
            if (level >= 4) {
                pt.set(4,4);
            } else if (level >= 1) {
                pt.set(2,3);
            }
        }
    },
    new MagicStatic(MagicLayer.Ability) {
        @Override
        public void modAbilityFlags(final MagicPermanent source, final MagicPermanent permanent, final Set<MagicAbility> flags) {
            final int level = permanent.getCounters(MagicCounterType.Level);
            if (level >= 4) {
                permanent.addAbility(MagicAbility.Vigilance, flags);
            }
            if (level >= 1) {
                permanent.addAbility(MagicAbility.Flying, flags);
            }
        }
    }
]
