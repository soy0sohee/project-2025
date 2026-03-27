export default function SkeletonCard() {
  return (
    <div className="flex flex-col bg-[#16213e] rounded-2xl overflow-hidden border border-[#0f3460]">
      <div className="w-full aspect-[2/3] skeleton" />
      <div className="p-3 flex flex-col gap-2">
        <div className="h-4 rounded skeleton" />
        <div className="h-4 rounded skeleton w-3/4" />
        <div className="h-7 rounded-lg skeleton mt-2" />
      </div>
    </div>
  );
}
