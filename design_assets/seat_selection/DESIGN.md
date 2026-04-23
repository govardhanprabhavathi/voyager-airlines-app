# Design System Strategy: The Elevated Voyager

## 1. Overview & Creative North Star
**Creative North Star: "The Digital Concierge"**

This design system moves away from the utilitarian "grid of boxes" common in travel apps. Instead, it adopts an editorial, high-end aesthetic that mirrors the experience of a premium airport lounge. We are moving beyond Material Design 3’s defaults to create a "Digital Concierge" experience—one that feels bespoke, calm, and authoritative. 

The system breaks the template look through **intentional asymmetry** (e.g., hero headers that bleed off-edge), **tonal depth** (replacing lines with layers), and **high-contrast typography scales** that prioritize legibility while maintaining a fashion-forward editorial feel. We don't just "show flights"; we curate a journey.

---

## 2. Colors & Surface Philosophy
The palette is rooted in a deep, trustworthy Indigo (`primary: #0040a1`) paired with a high-energy Secondary (`secondary: #ac3509`) for critical actions.

### The "No-Line" Rule
**Explicit Instruction:** 1px solid borders for sectioning are strictly prohibited. 
Boundaries must be defined solely through background color shifts or tonal transitions. For example, a flight search module should sit as a `surface-container-lowest` block atop a `surface-container-low` background. This creates a softer, more sophisticated interface that feels "carved" rather than "drawn."

### Surface Hierarchy & Nesting
Treat the UI as a series of physical layers—like stacked sheets of fine, heavy-stock paper.
*   **Base Level:** `surface` (#f8f9fc) for global backgrounds.
*   **Secondary Level:** `surface-container-low` (#f2f4f6) for grouping related content (e.g., flight result lists).
*   **Priority Level:** `surface-container-lowest` (#ffffff) for individual interactive cards.

### The "Glass & Gradient" Rule
To escape the "standard app" feel, use **Glassmorphism** for floating elements like the bottom navigation bar or top-pinned flight filters. Use a semi-transparent `surface` color with a 20px backdrop-blur. 
*   **Signature Textures:** Apply a subtle linear gradient (from `primary` to `primary_container`) on main CTAs and the Search Hero background to provide a sense of atmospheric depth (the "sky" effect).

---

## 3. Typography
We utilize a dual-font strategy to balance character with functional clarity.

*   **Display & Headlines (Manrope):** Chosen for its wide, modern geometric stance. Use `headline-lg` for destination titles to evoke a sense of "arrival." 
*   **Body & Labels (Inter):** The workhorse. Inter provides exceptional legibility at small sizes (crucial for flight times and gate numbers).

**The Editorial Scale:** 
Use a high contrast between `headline-md` (for titles) and `label-sm` (for secondary metadata). This hierarchy guides the eye to the most important information—the price and the destination—without cluttering the mental space.

---

## 4. Elevation & Depth
In this system, depth is a function of light and layering, not "drop shadows."

*   **The Layering Principle:** Depth is achieved by "stacking" the surface-container tiers. Place a `surface-container-lowest` card on a `surface-container-low` section to create a soft, natural lift.
*   **Ambient Shadows:** For floating action buttons or high-priority modals, use "Ambient Shadows." These must be extra-diffused (Blur: 24px, Spread: -4px) and low-opacity (4%-8%). The shadow color should be a tinted version of `on-surface` (#191c1e) to mimic natural light.
*   **The Ghost Border Fallback:** If a border is required for accessibility, use the "Ghost Border": `outline-variant` at 15% opacity. Never use 100% opaque borders.

---

## 5. Components

### Flight Cards & Results
*   **Layout:** Forbid the use of divider lines. Separate "Departure" and "Arrival" sections using vertical white space (1.5rem) or a subtle background shift to `surface-container-high`.
*   **The "Cheapest" Badge:** Use `tertiary_fixed` (#69ff87) with `on_tertiary_fixed` text. This provides a "Mint Green" highlight that feels fresh, not jarring.

### Search Inputs (Prominent)
*   **Style:** Inputs should be large, utilizing `surface-container-highest` for the background with a 12px (`md`) corner radius.
*   **State:** On focus, transition the background to `surface-container-lowest` and apply a subtle `primary` ambient shadow.

### Buttons (CTAs)
*   **Primary (Book Now):** Use a gradient of `primary` to `primary_container`. High-gloss finish with `on_primary` text.
*   **Secondary (View Details):** A "Ghost" style. No fill, only a 10% opaque `primary` background on press.

### Chips (Selection)
*   **Filter Chips:** Use `surface-container-high` as the default state. When selected, transition to `primary` with `on_primary` text. Avoid the "pill" shape; use the `md` (0.75rem) corner radius for a more architectural look.

### Success States (Confirmation)
*   **Experience:** Use full-bleed `tertiary_container` backgrounds. Incorporate a "Glass" card in the center to hold the ticket details, creating a celebratory, "rewarding" feeling for the user.

---

## 6. Do's and Don'ts

### Do:
*   **Do** use 24px or 32px of padding on the edges of flight cards to create a "premium" sense of space.
*   **Do** use `primary_fixed_dim` for icons in an inactive state—it keeps the "sky" theme consistent even in the background.
*   **Do** align all text to a strict 4dp baseline grid to ensure the editorial look feels intentional and not messy.

### Don't:
*   **Don't** use pure black (#000000) for text. Always use `on_surface` or `on_surface_variant` to keep the contrast sophisticated.
*   **Don't** use standard Material 3 "elevated" shadows. They are too heavy for this "light as air" airline aesthetic.
*   **Don't** use dividers between list items. Use the `surface` color shifts to define the end of one item and the start of another.