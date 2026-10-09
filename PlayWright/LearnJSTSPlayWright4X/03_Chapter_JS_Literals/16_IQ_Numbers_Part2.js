// ======================
// 3. Numeric Separators
// ======================

let million = 1_000_000; // 1 million
let binarySep = 0b1010_0001; // binary with separator
let hexSep = 0xFF_FF; // hexadecimal with separator

console.log("Million:", million);
console.log("Binary with separator:", binarySep);
console.log("Hexadecimal with separator:", hexSep);

// =============
// 4. BIGIT - For arbitarily large integers
// =============

let big = 1234567890123456789012345678901234567890n; // BigInt literal
let big2 = BigInt("1234567890123456789012345678901234567890"); // BigInt from string
let bigFromNum = BigInt(42); // BigInt from number

console.log("BigInt literal:", big);
console.log("BigInt from string:", big2);
console.log("BigInt from number:", bigFromNum);
console.log("Type of big:", typeof big); // "bigint"