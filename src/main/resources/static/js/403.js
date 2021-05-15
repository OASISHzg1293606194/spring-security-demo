// target the elements in the DOM used in the project
const ghost = document.querySelector(".ghost");
const heading = document.querySelector("h1");
const paragraph = document.querySelector("p");

// for the length of the timout, consider the --animation-duration custom property and add a small delay
// retrieve properties on the root element
const root = document.querySelector(":root");
const rootStyles = getComputedStyle(root);

// retrieve the animation-duration custom property
// ! this is specified as "40s", in seconds, so parse the number and includ it in milliseconds
const animationDuration = parseInt(rootStyles.getPropertyValue("--animation-duration")) * 1000;
