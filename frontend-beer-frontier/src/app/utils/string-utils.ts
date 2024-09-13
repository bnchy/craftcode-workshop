export function transformEnumValue(value: string): string {
  const words = value.toLowerCase().split('_');
  const transformed = words.map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');

  return transformed;
}