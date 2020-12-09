from functools import partial
import torch
import os
import PIL
from typing import Any, Callable, List, Optional, Union, Tuple
from torchvision.datasets.vision import VisionDataset


class VAEWAVE(VisionDataset):
    def __init__(
        self,
        root: str,
        split: str = "train",
        target_type: Union[List[str], str] = "attr",
        transform: Optional[Callable] = None,
        target_transform: Optional[Callable] = None,
        download: bool = False,
    ) -> None:
        from glob import glob
        super(VAEWAVE, self).__init__(root, transform=transform,
                                    target_transform=target_transform)
        self.root = root
        self.filelist = glob(os.path.join(root,'*.png'))
        self.file_len = len(self.filelist)
        print(self.file_len)
        self.broken_list = [1110]
    
    def __getitem__(self, index: int) -> Tuple[Any, Any]:
        X = PIL.Image.open(self.filelist[index]).convert("RGB")
        target = torch.tensor(0) if index not in self.broken_list else torch.tensor(1)

        if self.transform is not None:
            X = self.transform(X)
        # if target:
        #     target = tuple(target) if len(target) > 1 else target[0]

        #     if self.target_transform is not None:
        #         target = self.target_transform(target)
        # else:
        #     target = None

        return X, target

    def __len__(self):
        return self.file_len

